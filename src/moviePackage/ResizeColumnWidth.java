package moviePackage;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ResizeColumnWidth {
	
	public ResizeColumnWidth(JTable table) {

		final TableColumnModel columnModel = table.getColumnModel();
		for(int col=0; col < table.getColumnCount(); col++) {
			int width = 50;
			for(int row=0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, col);
				Component comp = table.prepareRenderer(renderer, row, col);
				width = Math.max(comp.getPreferredSize().width+1, width);
			}
			columnModel.getColumn(col).setPreferredWidth(width);
		}
	}
}
