(ns tests.web.controllers.app-test
  (:require
    [cljs.test :as t]
    [app.web.controllers.app :as app]))

(t/deftest should-return-success-true
  (let [result (app/health)]
    (t/is (== result {:success true}))))
