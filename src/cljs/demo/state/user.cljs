(ns demo.state.user
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
    [javelin.core]
    [castra.core :refer [mkremote]]))

(defc error nil)
(defc loading [])

(defc users {})


(def list-of-users
  (mkremote 'demo.api.user/list-of-users users error loading))


(defn init []
  #_(list-of-users)
  (js/setInterval list-of-users 1000))
