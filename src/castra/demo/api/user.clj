(ns demo.api.user
    (:require
      [demo.datomic.api :refer [fetch-random-record fetch-record update-record! insert-record! first-and-last-names]]
      [castra.core :refer [defrpc]]))

(defrpc get-random-user []
  (fetch-random-record))

(defrpc get-user [id]
  (fetch-record id))

(defrpc update-user [user-data]
  {:rpc/pre [(update-record! user-data)]}
  (fetch-record (:db/id user-data)))

(defrpc insert-user [user-data]
  {:rpc/pre [(insert-record! user-data)]}
  (fetch-record (:db/id user-data)))

(defrpc list-of-users []
  (first-and-last-names))
