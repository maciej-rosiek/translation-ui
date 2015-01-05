(ns translation-ui.utils
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(defn set-hash! [loc]
  (set! (.-hash js/window.location) loc))
