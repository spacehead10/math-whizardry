package combat.math;

import core.Media;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathString {
    private List<String> segments;
    private List<Integer> segmentWidths;
    private List<Boolean> segmentIsExponent;

    public MathString(String str) {
        //Note: Does not work with nested exponents e.g. e^(x^(2)); however, we don't really need to use those

        segments = new ArrayList<>();
        segmentWidths = new ArrayList<>();
        segmentIsExponent = new ArrayList<>();

        List<Integer> exponentStarts = new ArrayList<>();
        List<Integer> exponentEnds = new ArrayList<>();
        List<Integer> splits = new ArrayList<>();

        //find all of the exponent starts
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.substring(i, i + 2).equals("^{")) {
                exponentStarts.add(i);
                splits.add(i);
            }
        }

        //for each exponent start, find its end
        for (int s : exponentStarts) {
            for (int i = s; i < str.length(); i++) {
                if (str.charAt(i) == '}') {
                    exponentEnds.add(i);
                    splits.add(i);
                    break;
                }
            }
        }

        /*
            For multiple exponents, the previous loops make it so all of the start splits get placed before all of the end splits,
            so splits need to be sorted. Conveniently there's no need to copy a sorting method from Bubble since there's already
            one built in.
         */
        Collections.sort(splits);

        if (exponentStarts.size() != exponentEnds.size()) {
            //if the input starts an exponent but does not end it with closed braces, it is invalid
            segments.add("Invalid math expression!");
            segmentWidths.add(Media.defaultFontMedium.getWidth("Invalid math expression!"));
            segmentIsExponent.add(false);
        }
        else {
            if (splits.isEmpty()) {
                //if there are no exponents, just use the original string as is
                segments.add(str);
                segmentWidths.add(Media.defaultFontMedium.getWidth(str));
                segmentIsExponent.add(false);
            }
            else {
                //start of str -> start of first exponent
                segments.add(str.substring(0, splits.getFirst()));
                segmentWidths.add(Media.defaultFontMedium.getWidth(str.substring(0, splits.getFirst())));
                segmentIsExponent.add(false);

                //run through every segment between the start of the first exponent to the end of the last exponent
                boolean isExponent = true;
                for (int i = 0; i < splits.size() - 1; i++) {
                    /*
                        Reminder:
                        Exponent start splits occur at '^', so substring(start + 2, ...) excludes "^{"
                        Exponent end splits occur at '}', so substring(end + 1, ...) excludes '}'
                    */
                    if (isExponent) {
                        String s = str.substring(splits.get(i) + 2, splits.get(i + 1));
                        segments.add(s);
                        segmentWidths.add(Media.defaultFontSmall.getWidth(s));
                        segmentIsExponent.add(true);
                    }
                    else {
                        String s = str.substring(splits.get(i) + 1, splits.get(i + 1));
                        segments.add(s);
                        segmentWidths.add(Media.defaultFontMedium.getWidth(s));
                        segmentIsExponent.add(false);
                    }
                    isExponent = !isExponent; //assumes a normal segment will always follow an exponent, and vice versa; this is ok as long as there are no nested exponents
                }

                //end of last exponent -> end of str
                if (splits.getLast() + 1 < str.length()) {
                    segments.add(str.substring(splits.getLast() + 1));
                    segmentWidths.add(Media.defaultFontMedium.getWidth(str.substring(splits.getLast() + 1)));
                    segmentIsExponent.add(false);
                }
            }
        }
    }

    public void draw(float x, float y, Graphics g) {
        for (int i = 0; i < segments.size(); i++) {
            if (segmentIsExponent.get(i)) {
                g.setFont(Media.defaultFontSmall);
                g.drawString(segments.get(i), x, y);
            }
            else {
                g.setFont(Media.defaultFontMedium);
                g.drawString(segments.get(i), x, y);
            }
            x += segmentWidths.get(i);
        }
    }
}
