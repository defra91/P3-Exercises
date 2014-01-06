import java.awt.Image;

public class SingleThreadRenderer {
	static void renderPage(CharSequence source) {

		RenderThread t1 = new RenderThread(source);
		RenderThread t2 = new RenderThread(source);
		t1.start();
		t2.start();
		t1.join(); t2.join();
		for (Image img : imageList)
			renderImage(img);
	}

	class RenderThread extends Thread {
		public RenderThread(CharSequence s) {
			source = s;
		}
		CharSequence source;
		public void run() {
			renderText(source);
		}
	}

	class DownloadThread extends Thread {
		public DownloadThread(CharSequence s) { source = s; }
		CharSequence source;
		public void run() {
			List<ImageInfo> imageInfoList = scanForImageInfo(source);
			for (ImageInfo imageInfo : imageInfoList)
				imageList.add(imageInfo.downloadImage);
		}
	}

	static void renderText(CharSequence s) {}
	static void scanForImageInfo(CharSequence s) {}
	static void renderImage(Image s) {}
}