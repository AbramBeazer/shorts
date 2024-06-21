package org.shorts.model.items;

public class ZoomLens extends HeldItem {

    private ZoomLens() {
        super("Zoom Lens", 10);
    }

    public static final ZoomLens ZOOM_LENS = new ZoomLens();
}
