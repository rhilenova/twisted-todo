package space.rhilenova.apps.twisted.gui;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * TODO: Document
 * 0, 1, 2
 * 3,    4
 * 5, 6, 7
 */
class PartialLineBorder extends AbstractBorder
{
    /** The edges to draw. */
    private List<Integer> _sides;
    /** The color to draw the lines. */
    private final Color _color;
    /** The width of the lines to draw. */
    private int _thickness;

    /** The top sides of the border. */
    private static final Integer[] TOP = {0, 1, 2};
    /** The left sides of the border. */
    private static final Integer[] LEFT = {0, 3, 5};
    /** The bottom sides of the border. */
    private static final Integer[] BOTTOM = {5, 6, 7};
    /** The right sides of the border. */
    private static final Integer[] RIGHT = {2, 4, 7};

    /**
     * A partial border, allowing for edges and corners to be specified individually.
     *
     * @param sides A list of sides to draw. See class documentatin for more info.
     * @param color The color to draw the lines.
     * @param thickness The width of lines to draw.
     */
    PartialLineBorder(List<Integer> sides, Color color, int thickness)
    {
        this._sides = sides;
        this._color = color;
        this._thickness = thickness;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        if (this._thickness > 0 && g instanceof Graphics2D)
        {
            Graphics2D g2d = (Graphics2D)(g);

            Color old_color = g2d.getColor();
            g2d.setColor(this._color);
            g2d.drawRect(c.getX(), c.getY(), this._thickness, this._thickness);

            // TODO draw requested borders
            g2d.setColor(old_color);
        }
    }

    public Insets getBorderInsets(Component c)
    {
        int top_in = 0;
        int left_in = 0;
        int bottom_in = 0;
        int right_in = 0;

        if (!CollectionUtils.intersection(this._sides, Arrays.asList(PartialLineBorder.TOP)).isEmpty())
        {
            top_in = this._thickness;
        }
        if (!CollectionUtils.intersection(this._sides, Arrays.asList(PartialLineBorder.LEFT)).isEmpty())
        {
            left_in = this._thickness;
        }
        if (!CollectionUtils.intersection(this._sides, Arrays.asList(PartialLineBorder.BOTTOM)).isEmpty())
        {
            bottom_in = this._thickness;
        }
        if (!CollectionUtils.intersection(this._sides, Arrays.asList(PartialLineBorder.RIGHT)).isEmpty())
        {
            right_in = this._thickness;
        }

        return new Insets(top_in, left_in, bottom_in, right_in);
    }
}
