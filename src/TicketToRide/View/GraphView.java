package TicketToRide.View;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

import TicketToRide.Model.City;
import TicketToRide.Model.Constants;
import TicketToRide.Model.Path;
import TicketToRide.Model.World;

/**
 * @author Sean Fast This class creates the graph of cities and routes from the
 *         nodes and edges stored in World object
 */
public class GraphView extends javax.swing.JPanel {
	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);

		// Draw vertices
		List<City> vertices = World.cities;
		for (int i = 0; i < vertices.size(); i++) {
			int x = vertices.get(i).getX_val();
			int y = vertices.get(i).getY_val();
			String name = vertices.get(i).getCityName();

			g.fillOval(x - 8, y - 8, 16, 16);
			g.drawString(name, x - 12, y - 12);
		}

		// Draw edges
		List<Path> edges = World.map;
		for (int i = 0; i < edges.size(); i++) {
			int x1 = edges.get(i).getCity1().getX_val();
			int y1 = edges.get(i).getCity1().getY_val();
			int x2 = edges.get(i).getCity2().getX_val();
			int y2 = edges.get(i).getCity2().getY_val();
			int cost = edges.get(i).getCost();
			Constants.pathColor pathColor = edges.get(i).getColor();

			g.setColor(pathColor.getRealColor());

			Graphics2D g2D = (Graphics2D) g;
			float[] dashingPattern1 = { 6f, 6f };
			Stroke unclaimedRoute = new BasicStroke(3F, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);
			Stroke claimedRoute = new BasicStroke(4F);

			if (edges.get(i).getOwningPlayer() == null) {
				// draw dashed if route is unclaimed
				g2D.setStroke(unclaimedRoute);
				g.drawString(cost + "", (x1 + x2) / 2, (y1 + y2) / 2 - 6);
			} else {
				// draw solid if route is claimed
				g.setColor(edges.get(i).getOwningPlayer().getColor()
						.getRealColor());
				g2D.setStroke(claimedRoute);
			}

			g2D.drawLine(x1, y1, x2, y2);
		}
	}
}
