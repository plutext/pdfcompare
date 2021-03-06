package de.redsix.pdfcompare;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

/**
 * A CompareResult, that also stores the expected and actual Image and also keeps diffImages in memory for later display.
 */
public class CompareResultWithExpectedAndActual extends CompareResult {

    private final Map<Integer, ImageWithDimension> expectedImages = new TreeMap<>();
    private final Map<Integer, ImageWithDimension> actualImages = new TreeMap<>();

    @Override
    public void addPage(final boolean hasDifferences, final boolean hasDifferenceInExclusion, final int pageIndex,
            final ImageWithDimension expectedImage, final ImageWithDimension actualImage, final ImageWithDimension diffImage) {
        super.addPage(hasDifferences, hasDifferenceInExclusion, pageIndex, expectedImage, actualImage, diffImage);
        expectedImages.put(pageIndex, expectedImage);
        actualImages.put(pageIndex, actualImage);
    }

    @Override
    protected boolean keepImages() {
        return true;
    }

    public synchronized BufferedImage getDiffImage(final int page) {
        return diffImages.get(page).bufferedImage;
    }

    public BufferedImage getExpectedImage(final int page) {
        return expectedImages.get(page).bufferedImage;
    }

    public BufferedImage getActualImage(final int page) {
        return actualImages.get(page).bufferedImage;
    }
}
