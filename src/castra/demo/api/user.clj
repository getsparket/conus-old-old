(ns demo.api.user
    (:require
      [demo.datomic.api :refer [fetch-random-record fetch-record insert-record! fetch-all-things]]
      [castra.core :refer [defrpc]]))

(defrpc get-random-user []
  (fetch-random-record))

(defrpc insert-user [user-data]
  {:rpc/pre [(insert-record! user-data)]}
  (fetch-random-record))

(defrpc get-all-things []
  (fetch-all-things))
