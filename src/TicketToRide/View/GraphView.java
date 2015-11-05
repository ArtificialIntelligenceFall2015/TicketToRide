package TicketToRide.View;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import TicketToRide.Model.City;
import TicketToRide.Model.World;

public class GraphView extends javax.swing.JPanel {
    Graph<? extends Displayable> graph;
    
    public GraphView(Graph<? extends Displayable> graph) {
      this.graph = graph;
    }
    
    @Override
    protected void paintComponent(java.awt.Graphics g) {
      super.paintComponent(g);
      
      // Draw vertices
      //List<City> vertices = World.city;
      
//      for (int i = 0; i < graph.getSize(); i++) {
//        int x = vertices.get(i).getX();
//        int y = vertices.get(i).getY();
//        String name = vertices.get(i).getName();
//        
//        g.fillOval(x - 8, y - 8, 16, 16);
//        g.drawString(name, x - 12, y - 12);
//      }
      
      
      
      // Draw edges
      for (int i = 0; i < graph.getSize(); i++) {
        List<Integer> neighbors = graph.getNeighbors(i);
        for (int j = 0; j < neighbors.size(); j++) {
          int v = neighbors.get(j);
          int x1 = graph.getVertex(i).getX();
          int y1 = graph.getVertex(i).getY();
          int x2 = graph.getVertex(v).getX();
          int y2 = graph.getVertex(v).getY();
          
          Graphics2D g2D = (Graphics2D) g;      
         // g2D.setStroke(new BasicStroke(10F));  // set stroke width of 10
         // g2D.drawLine(x1, y1, x2, y2);
          //g.setColor(Color.RED);
         g.drawLine(x1, y1, x2, y2);
        }
      }
      
      // Display weights
      List<PriorityQueue<WeightedEdge>> queues = 
        ((WeightedGraph<? extends Displayable>)graph).getWeightedEdges(); 
      
      for (int i = 0; i < graph.getSize(); i++) {
        ArrayList<WeightedEdge> list = new ArrayList<WeightedEdge>(queues.get(i));
        
        for (WeightedEdge edge: list) {
          int u = edge.u;
          int v = edge.v;
          double weight = edge.weight;
          int x1 = graph.getVertex(u).getX();
          int y1 = graph.getVertex(u).getY();
          int x2 = graph.getVertex(v).getX();
          int y2 = graph.getVertex(v).getY();
          g.drawString(weight + "", (x1 + x2) / 2, (y1 + y2) / 2 - 6);
        }
      }
    }
  }
