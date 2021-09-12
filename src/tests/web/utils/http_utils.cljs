(ns tests.web.utils.http-utils
  (:require
    ["fastify" :as fastify]
    [app.web.router :as r]
    [cljs.core.async :as async]
    [cljs.core.async.interop :refer-macros [<p!]]))

(defn- make-app []
  (let [app (fastify)]
    (r/router app)
    app))

(defn GET! [route]
  (let [app (make-app)
        response (.inject app #js {:method "GET"
                                   :url route})]
    response))

(defn to-map [response]
  (let [result (js->clj response :keywordize-keys true)
        body (js->clj (js/JSON.parse (:body result)) :keywordize-keys true)]
    (assoc result :body body)))
