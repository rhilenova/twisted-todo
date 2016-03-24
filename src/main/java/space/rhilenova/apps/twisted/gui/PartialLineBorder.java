package space.rhilenova.apps.twisted.gui;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

/**
 * A variant of LineBorder, where the sides and corners can be selected individually.
 *
 * The specification of which parts to draw is done through the "sides" argument of the constructor. Which number
 * corresponds to which side is as follows:
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
     * @param corner_height The height of the corners.
     * @param corner_width The width of the corners.
     */
    PartialLineBorder(List<Integer> sides, Color color, int thickness, int corner_height, int corner_width)
    {
        this._sides = sides;
        this._color = color;
        this._thickness = thickness;
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
            int corner_width = this._corner_width;
            int line_width = c.getWidth() - (2 * corner_width);
            int corner_height = this._corner_height;
            int line_height = c.getHeight() - (2 * corner_height);

            // TopLeft
            if (this._sides.contains(0))
            {
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float();
                path.append(new Rectangle(0, 0, corner_width, this._thickness), false);
                path.append(new Rectangle(0, 0, this._thickness, corner_height), false);
                g2d.fill(path);
            }
            // Top
            if (this._sides.contains(1))
            {
                Shape rect = new Rectangle(corner_width, 0, line_width, this._thickness);
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float(rect);
                g2d.fill(path);
            }
            // TopRight
            if (this._sides.contains(2))
            {
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float();
                path.append(new Rectangle(0, 0, corner_width, this._thickness), false);
                path.append(new Rectangle(corner_width - this._thickness, 0, this._thickness, corner_height), false);
                path.transform(AffineTransform.getTranslateInstance(corner_width + line_width, 0f));
                g2d.fill(path);
            }
            // Left
            if (this._sides.contains(3))
            {
                Shape rect = new Rectangle(0, corner_height, this._thickness, line_height);
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float(rect);
                g2d.fill(path);
            }
            // Right
            if (this._sides.contains(4))
            {
                Shape rect = new Rectangle(c.getWidth() - this._thickness, corner_height, this._thickness, line_height);
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float(rect);
                g2d.fill(path);
            }
            // BottomLeft
            if (this._sides.contains(5))
            {
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float();
                path.append(new Rectangle(0, corner_height - this._thickness, corner_width, this._thickness), false);
                path.append(new Rectangle(0, 0, this._thickness, corner_height), false);
                path.transform(AffineTransform.getTranslateInstance(0f, corner_height + line_height));
                g2d.fill(path);
            }
            // Bottom
            if (this._sides.contains(6))
            {
                Shape rect = new Rectangle(corner_width, c.getHeight() - this._thickness, line_width, this._thickness);
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float(rect);
                g2d.fill(path);
            }
            // BottomRight
            if (this._sides.contains(7))
            {
                java.awt.geom.Path2D.Float path = new java.awt.geom.Path2D.Float();
                path.append(new Rectangle(0, corner_height - this._thickness, corner_width, this._thickness), false);
                path.append(new Rectangle(corner_width - this._thickness, 0, this._thickness, corner_height), false);
                path.transform(AffineTransform.getTranslateInstance(corner_width + line_width, corner_height + line_height));
                g2d.fill(path);
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
