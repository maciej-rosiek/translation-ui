(ns translation-ui.app
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [secretary.core :as secretary]
            [translation-ui.utils :as utils]))

(defn handle-search [event app owner]
  (let [keyword (.. event -target -value)]
    (utils/set-hash! (str "/search/" keyword))))

(defn search [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div #js {:className "search"}
               (dom/input #js {:type "text" :ref "keyword" :onChange #(handle-search % app owner) :value (:keyword app)})))))
