  
(ns app.core
  (:require ["fastify" :as fastify]))

;; currently broken in shadow-cljs
(set! *warn-on-infer* true)

(defonce server (atom nil))

(defn on-get [request reply]
  (.send reply "Hello there"))

(defn start-server []
  (println "Starting server")
  (let [app (fastify {:logger true})]
    (.get app "/" on-get)
    (.listen app 3000)))

(defn on-listen []
  (println "App is running on port 3000!"))

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
