(ns tests.web.routes.app-routes-test
  (:require
    ["fastify" :as fastify]
    [app.web.router :as r]
    [cljs.test :as t]
    [cljs.core.async :refer [go]]
    [cljs.core.async.interop :refer-macros [<p!]]))

(defn make-server []
  (let [app (fastify)]
    (r/router app)
    app))

(defn GET! [route app]
  (go 
    (let [response (<p!
                     (.inject app (clj->js {:method "GET"
                                            :url route})))]
      (js->clj (<p! response)))))

(t/deftest should-get-health
  (go 
    (let [server (make-server)
          result (<p! (GET! "/health" server))]
      
      (t/is 
        (== result {:success true})))))
