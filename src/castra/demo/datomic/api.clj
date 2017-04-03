(ns demo.datomic.api
  (:require
    [demo.datomic.db :refer [datomic-conn]]
    [datomic.api :as d]))

(defn fetch-ids
   "Find all the IDs in the database"
   []
   (d/q '[:find ?e :where [?e :person/user-name]] (d/db datomic-conn)))

#_(defn user-names []
  {:Ellen 2, :Bob 2, :Dustin 2, :Claire 2})
#_(defn user-names []
  (zipmap (map keyword (map first (d/q '[:find ?first :where
                                         [?e :person/user-name ?first]]  (d/db datomic-conn)))) (repeat "hello")))

(defn- random-id
  "Choose a random ID from the database"
  []
  (rand-nth (flatten (map identity (fetch-ids)))))

(defn user-names
  "Use the pull API to fetch all attributes for a random ID"
  []
  #_(println "Fetching record for id:" id)
  (d/pull (d/db datomic-conn) '[:*] 17592186045418))

(defn fetch-random-record
  "Use the pull API to fetch all attributes for a random ID"
  []
  (fetch-record (random-id)))
