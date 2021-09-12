(ns app.core
  (:require
    ["fastify" :as fastify]
    [app.web.router :as r]))

(enable-console-print!)
(set! *warn-on-infer* true)

(defonce server (volatile! nil))

(defn start-server []
  (let [app (fastify {:logger true})]
    (r/router app)
    (.listen app 3000 (fn [err]
                        (js/console.log "Server started")
                        (vreset! server app)))))


(defn start! []
  (js/console.warn "Starting server")
  (start-server))

(defn stop! [done]
  (js/console.warn "Stopping server")
  (when-some [svr @server]
    (.close svr
            (fn [err]
              (js/console.warn "Server stopped" err)
              (done)))))

(defn main []
  (start!))
