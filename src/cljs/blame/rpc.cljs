(ns blame.rpc
  (:require-macros
    [javelin.core :refer [defc defc=]])
  (:require
   [javelin.core]
   [castra.core :refer [mkremote]]))

(defc state {})
(defc error nil) ;; TODO
(defc loading [])

(def get-state
  (mkremote 'blame.api/get-thing state error loading))

(def new-owner
  (mkremote 'blame.api/new-owner-for-thing state error loading))

(def add-new
  (mkremote 'blame.api/add-another-thing state error loading))

(defn init []
  (get-state)
  (js/setInterval get-state 1000))
