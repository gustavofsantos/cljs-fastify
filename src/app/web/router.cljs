(ns app.web.router
  (:require
    [app.web.controllers.app :as app]))

(defn router [^js app]
  (.get app "/health"
        (fn [_ reply]
          (.send reply (clj->js (app/health))))))
