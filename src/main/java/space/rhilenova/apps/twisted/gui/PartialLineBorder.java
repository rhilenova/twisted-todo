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

    /** The divisor to use for calculating corner sizes. */
    private float _divisor;
    /** The height of the corners. */
    private int _corner_height;
    /** The width of the corners. */
    private int _corner_width;

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
     * @param sides A list of sides to draw. See class documentation for more info.
     * @param color The color to draw the lines.
     * @param thickness The width of lines to draw.
     * @param divisor The divisor to use for calculating corner sizes.
     */
    PartialLineBorder(List<Integer> sides, Color color, int thickness, float divisor)
    {
        this._sides = sides;
        this._color = color;
        this._thickness = thickness;

        this._divisor = divisor;
        this._corner_height = 0;
        this._corner_width = 0;
    }

    /**
     * A partial border, allowing for edges and corners to be specified individually.
     *
     * @param sides A list of sides to draw. See class documentation for more info.
     * @param color The color to draw the lines.
     * @param thickness The width of lines to draw.
     * @param corner_height The height of the corners.
     * @param corner_width The width of the corners.
     */
    PartialLineBorder(List<Integer> sides, Color color, int thickness, int corner_height, int corner_width)
    {
        this._sides = sides;
        this._color = color;
        this._thickness = thickness;

        this._divisor = 0;
        this._corner_height = corner_height;
        this._corner_width = corner_width;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        if (this._thickness > 0 && g instanceof Graphics2D)
        {
            Graphics2D g2d = (Graphics2D)(g);

            Color old_color = g2d.getColor();
            g2d.setColor(this._color);

            // Get width of line and corners.
            int corner_width;
            int line_width;
            int corner_height;
            int line_height;
            if (_divisor != 0)
            {
                corner_width = Math.round(c.getWidth() / this._divisor);
                line_width = c.getWidth() - (2 * corner_width);
                corner_height = Math.round(c.getHeight() / this._divisor);
                line_height = c.getHeight() - (2 * corner_height);
            }
            else
            {
                corner_width = this._corner_width;
                line_width = c.getWidth() - (2 * corner_width);
                corner_height = this._corner_height;
                line_height = c.getHeight() - (2 * corner_height);
            }

            // TopLeft
            if (this._sides.contains(0))
            {
                // Horiz
                g2d.drawRect(0, 0, corner_width, this._thickness - 1);
                // Vert
                g2d.drawRect(0, 0, this._thickness - 1, corner_height);
            }
            // Top
            if (this._sides.contains(1))
            {
                g2d.drawRect(corner_width, 0,
                             line_width, this._thickness - 1);
            }
            // TopRight
            if (this._sides.contains(2))
            {
                // Horiz
                g2d.drawRect(corner_width + line_width, 0,
                             corner_width, this._thickness - 1);
                // Vert
                g2d.drawRect(c.getWidth() - this._thickness, 0,
                             this._thickness - 1, corner_height);
            }
            // Left
            if (this._sides.contains(3))
            {
                g2d.drawRect(0, corner_height,
                             this._thickness - 1, line_height);
            }
            // Right
            if (this._sides.contains(4))
            {
                g2d.drawRect(c.getWidth() - this._thickness, corner_height,
                             this._thickness - 1, line_height);
            }
            // BottomLeft
            if (this._sides.contains(5))
            {
                // Horiz
                g2d.drawRect(0, c.getHeight() - this._thickness,
                             corner_width, this._thickness - 1);
                // Vert
                g2d.drawRect(0, corner_height + line_height - 1,
                             this._thickness - 1, corner_height);
            }
            // Bottom
            if (this._sides.contains(6))
            {
                g2d.drawRect(corner_width, c.getHeight() - this._thickness,
                             line_width, this._thickness - 1);
            }
            // BottomRight
            if (this._sides.contains(7))
            {
                // Horiz
                g2d.drawRect(corner_width + line_width, c.getHeight() - this._thickness,
                             corner_width, this._thickness - 1);
                // Vert
                g2d.drawRect(c.getWidth() - this._thickness, corner_height + line_height - 1,
                             this._thickness - 1, corner_height);
            }

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
