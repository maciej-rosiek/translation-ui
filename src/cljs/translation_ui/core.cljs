(ns translation-ui.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [secretary.core :as secretary :include-macros true :refer-macros [defroute]]
            [translation-ui.app :as app]
            [goog.events :as events]
            [translation-ui.utils :as utils])
  (:import goog.History
           goog.history.EventType))

(def app-state (atom {:keyword ""}))

(def root
  (js/document.getElementById "app"))

(defroute "/" []
          (js/console.log (str "Main screen"))
          (utils/set-hash! "/search"))

(defroute "/search/:keyword" [keyword]
          (swap! app-state assoc :keyword keyword))

(let [h (History.)]
  (goog.events/listen h EventType.NAVIGATE #(secretary/dispatch! (.-token %)))
  (doto h
    (.setEnabled true)))

(defn main [] (om/root
                (fn [data owner] (app/search data owner))
                app-state
                {:target root}))