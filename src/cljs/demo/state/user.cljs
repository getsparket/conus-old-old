(ns demo.state.user
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
    [javelin.core]
    [castra.core :refer [mkremote]]))

(defc state {})
(defc has-all-things {})
(defc error nil)
(defc loading [])

(def get-all-things
  (mkremote 'demo.api.user/get-all-things has-all-things error loading))

(def get-random-user
  (mkremote 'demo.api.user/get-random-user state error loading))

(def insert-user
  (mkremote 'demo.api.user/insert-user state error loading))

(defn init []
  (get-all-things)
  (js/setInterval get-all-things 200)) ;; milliseconds. small values are more responsive, but consume CPU. TODO find another way.
