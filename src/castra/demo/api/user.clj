(ns demo.api.user
    (:require
      [demo.datomic.api :refer [fetch-random-record fetch-record  user-names]]
      [castra.core :refer [defrpc]]))

(defrpc get-random-user []
  (fetch-random-record))

(defrpc get-user [id]
  (fetch-record id))

#_(defrpc update-user [user-data]
  {:rpc/pre [(update-record! user-data)]}
  (fetch-record (:db/id user-data)))

#_(defrpc insert-user [user-data]
  {:rpc/pre [(insert-record! user-data)]}
  (fetch-record (:db/id user-data)))

(defrpc list-of-users []
  (user-names))
