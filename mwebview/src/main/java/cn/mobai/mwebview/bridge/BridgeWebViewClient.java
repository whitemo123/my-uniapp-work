package cn.mobai.mwebview.bridge;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class BridgeWebViewClient extends WebViewClient {
  private BridgeWebView webView;

  public BridgeWebViewClient(BridgeWebView webView) {
    this.webView = webView;
  }

  @Override
  public boolean shouldOverrideUrlLoading(WebView view, String url) {
    try {
      url = URLDecoder.decode(url, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    if (url.startsWith(BridgeUtil.WVJB_BRIDGE_LOADED)) {
      BridgeUtil.webViewLoadLocalJs(view, BridgeWebView.toLoadJs);

      if (webView.getStartupMessage() != null) {
        for (Message m : webView.getStartupMessage()) {
          webView.dispatchMessage(m);
        }
        webView.setStartupMessage(null);
      }
      return true;
    } else if (url.startsWith(BridgeUtil.WVJB_RETURN_DATA)) { // 如果是返回数据
      webView.handlerReturnData(url);
      return true;
    } else if (url.startsWith(BridgeUtil.WVJB_QUEUE_HAS_MESSAGE)) { // 消息队列有数据
      webView.flushMessageQueue();
      return true;
    } else {
      return super.shouldOverrideUrlLoading(view, url);
    }
  }

  /**
   * 无视ssl证书错误
   *
   * @param view
   * @param handler
   * @param error
   */
  @Override
  public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
    handler.proceed();
  }

  @Override
  public void onPageStarted(WebView view, String url, Bitmap favicon) {
    super.onPageStarted(view, url, favicon);
  }

  @Override
  public void onPageFinished(WebView view, String url) {
    super.onPageFinished(view, url);
  }

  @Override
  public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
    super.onReceivedError(view, errorCode, description, failingUrl);
  }
}
