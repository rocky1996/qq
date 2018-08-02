package com.acat.util;

import java.awt.Point;
import java.awt.Toolkit;

public class WindowXY {
	// ��Ѵ��ڵĿ�͸߸��ң�Ȼ���ҾͿ��԰������� ��Ҫ��ʾ��λ��
		public static Point getXY(int w, int h) {

			// �������Ļ�� ��͸�
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			int height = toolkit.getScreenSize().height;
			int width = toolkit.getScreenSize().width;

			int x = (width - w) / 2;
			int y = (height - h) / 2;

			Point p = new Point(x, y);
			return p;
		}

		public static Point getXY(java.awt.Dimension d) {
			return getXY(d.width, d.height);
		}
}
