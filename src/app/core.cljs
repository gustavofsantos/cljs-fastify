(ns app.core
  (:require
    ["fastify" :as fastify]
    [app.web.router :as r]))

(enable-console-print!)
(set! *warn-on-infer* true)

(defonce server (atom nil))

(defn on-listen []
  (println "App is running on port 3000!"))

(defn start-server []
  (println "Starting server")
  (let [app (fastify {:logger true})]
    (r/router app)
    (.listen app 3000 on-listen)))


(defn start! []
  ;; called by main and after reloading code
  (reset! server (start-server)))

(defn stop! []
  ;; called before reloading code
  (.close @server)
  (reset! server nil))

(defn main []
  ;; executed once, on startup, can do one time setup here
  (start!))
