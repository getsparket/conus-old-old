(ns demo.state.user
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
    [javelin.core]
    [castra.core :refer [mkremote]]))

(defc state {})
(defc error nil)
(defc loading [])

(defc users {})


(def list-of-users
  (mkremote 'demo.api.user/list-of-users users error loading))

(def get-random-user
  (mkremote 'demo.api.user/get-random-user state error loading))

(def get-user
  (mkremote 'demo.api.user/get-user state error loading))

(def update-user
  (mkremote 'demo.api.user/update-user state error loading))

(def insert-user
  (mkremote 'demo.api.user/insert-user state error loading))

(defn init []
  (list-of-users)
  (js/setInterval list-of-users 1000))
