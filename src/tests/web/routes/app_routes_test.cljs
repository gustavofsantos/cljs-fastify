(ns tests.web.routes.app-routes-test
  (:require
    [cljs.test :refer [async deftest testing is] :include-macros true]
    [tests.web.utils.http-utils :as utils]
    [cljs.core.async :refer [go]]
    [cljs.core.async.interop :refer-macros [<p!]]))

(deftest body-success
  (testing "GET /health should return { success: true } as body"
    (async done
          (go
            (let [response (<p! (utils/GET! "/health"))
                  result (utils/to-map response)
                  body (:body result)]
              (is (= {:success true} body))
              (done))))))

(deftest status-code-success 
  (testing "GET /health should return status code 200"
    (async done
           (go
             (let [response (<p! (utils/GET! "/health"))
                   result (utils/to-map response)
                   status-code (:statusCode result)]
               (is (= 200 status-code))
               (done))))))

