package TicketToRide.View;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.List;

import TicketToRide.Model.City;
import TicketToRide.Model.Constants;
import TicketToRide.Model.Path;
import TicketToRide.Model.World;

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
			Constants.pathColor color = edges.get(i).getColor();

			g.setColor(color.getRealColor());

			// g.drawLine(x1, y1, x2, y2);
			g.drawString(cost + "", (x1 + x2) / 2, (y1 + y2) / 2 - 6);

			Graphics2D g2D = (Graphics2D) g;
			float[] dashingPattern1 = { 6f, 6f };// ?
			Stroke stroke1 = new BasicStroke(3F, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashingPattern1,
					2.0f);// ?
			g2D.setStroke(stroke1);// ?
			// g2D.setStroke(new BasicStroke(3F)); // set stroke width
			g2D.drawLine(x1, y1, x2, y2);

		}
	}
}
