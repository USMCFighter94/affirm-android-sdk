package com.affirm.affirmsdk;

import android.support.annotation.NonNull;
import android.webkit.WebView;

public final class CheckoutWebViewClient extends AffirmWebViewClient {
  private final Callbacks callbacks;

  public CheckoutWebViewClient(@NonNull Callbacks callbacks) {
    super(callbacks);
    this.callbacks = callbacks;
  }

  @Override boolean hasCallbackUrl(WebView view, String url) {
    if (url.contains(AFFIRM_CONFIRMATION_URL)) {
      final String token = url.split("checkout_token=")[1];
      callbacks.onWebViewConfirmation(token);
      return true;
    } else if (url.contains(AFFIRM_CANCELLATION_URL)) {
      callbacks.onWebViewCancellation();
      return true;
    }

    return false;
  }

  public interface Callbacks extends AffirmWebViewClient.Callbacks {
    void onWebViewConfirmation(@NonNull String token);
  }
}
