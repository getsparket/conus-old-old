(ns demo.datomic.api
  (:require
    [demo.datomic.db :refer [datomic-conn]]
    [datomic.api :as d]))

(defn fetch-ids
   "Find all the IDs in the database"
   []
   (d/q '[:find ?e :where [?e :person/name]] (d/db datomic-conn)))

(defn- random-id
  "Choose a random ID from the database"
  []
  (rand-nth (flatten (map identity (fetch-ids)))))

(defn fetch-record
  "Use the pull API to fetch all attributes for a random ID"
  [id]
  (println "Fetching record for id:" id)
  (d/pull (d/db datomic-conn) '[:*] id))

(defn fetch-all-things
  []
  (d/q '[:find  (pull ?e [*])
         :where
         [?e :person/name]]
       (d/db datomic-conn)))

(defn fetch-random-record
  "Use the pull API to fetch all attributes for a random ID"
  []
  (fetch-record (random-id)))

(defn insert-record!
  "doo doo"
  [user-data]
  (let [user-data-fixed (assoc user-data :thing/price (read-string (:thing/price user-data)))
        noop (println "Inserting with " user-data)]
    @(d/transact datomic-conn (conj [] user-data-fixed))))
