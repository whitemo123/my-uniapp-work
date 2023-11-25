package cn.mobai.mwebview;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;

import androidx.annotation.NonNull;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.mobai.mwebview.bridge.BridgeWebView;
import io.dcloud.feature.uniapp.UniSDKInstance;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.ui.action.AbsComponentData;
import io.dcloud.feature.uniapp.ui.component.AbsVContainer;
import io.dcloud.feature.uniapp.ui.component.UniComponent;

/**
 * webview组件(uniapp)
 */
public class MWebViewComponent extends UniComponent<BridgeWebView> {
  // 全局上下文
  private Context globalContext;
  // 显示集合
  private Display[] displays;

  public MWebViewComponent(UniSDKInstance instance, AbsVContainer parent, AbsComponentData basicComponentData) {
    super(instance, parent, basicComponentData);
  }

  @Override
  protected BridgeWebView initComponentHostView(Context context) {
    globalContext = context;
    // 创建webview
    BridgeWebView webView = new BridgeWebView(this.globalContext);
    webView.setWebChromeClient(new WebChromeClient());
    // 权限申请
    XXPermissions.with(this.getContext())
      .permission(Permission.READ_MEDIA_AUDIO)
      .permission(Permission.READ_MEDIA_VIDEO)
      .permission(Permission.READ_MEDIA_IMAGES)
      .permission(Permission.WRITE_EXTERNAL_STORAGE)
      .permission(Permission.SYSTEM_ALERT_WINDOW)
      .request(new OnPermissionCallback() {
        @Override
        public void onGranted(@NonNull List<String> list, boolean b) {
//          if (b) {
//            loadAllDisplay();
//          }
        }

        @Override
        public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {
          if (doNotAskAgain) {
            XXPermissions.startPermissionActivity(context, permissions);
          }
        }
      });
    return webView;
  }

  /**
   * 通信显示数量
   */
  private void loadAllDisplay() {
    // 拿到所有显示设备集合
    DisplayManager displayManager = (DisplayManager) this.getContext().getSystemService(Context.DISPLAY_SERVICE);
    displays = displayManager.getDisplays();
    // 事件通知@onLoad显示器数量
    Map<String, Object> results = new HashMap<>();
    Map<String, Object> params = new HashMap<>();
    params.put("displays", displays.length);
    results.put("detail", params);
    fireEvent("onLoad", results);
  }

  /**
   * 加载组件
   * @param callback
   */
  @UniJSMethod
  public void init(UniJSCallback callback) {
    // 拿到所有显示设备集合
    DisplayManager displayManager = (DisplayManager) this.getContext().getSystemService(Context.DISPLAY_SERVICE);
    displays = displayManager.getDisplays();
    Map<String, Object> params = new HashMap<>();
    params.put("displays", displays.length);
    callback.invoke(params);
  }

  @UniJSMethod
  public void load(List<String> urls) {
    // 拿到所有显示设备集合
    for (int i = 0; i < displays.length; i++) {

    }
  }

  @Override
  public void onActivityResume() {
    super.onActivityResume();
  }

  @Override
  public void onActivityPause() {
    super.onActivityPause();
  }

  @Override
  public void onActivityDestroy() {
    super.onActivityDestroy();
  }

  private class SubScreen extends Presentation {

    private Context context;
    private BridgeWebView webView;

    public SubScreen(Context outerContext, Display display) {
      super(outerContext, display);
      context = outerContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      webView = new BridgeWebView(context);

      webView.setWebChromeClient(new WebChromeClient());

      webView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      setContentView(webView);
    }

    public void load(String url) {
      webView.loadUrl(url);
    }

    public BridgeWebView getWebView() {
      return webView;
    }
  }
}
