(ns app.web.controllers.app)

(defn health [_ reply]
  (.send reply (clj->js {:success true})))
