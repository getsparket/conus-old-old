(ns demo.datomic.seed
    (:require
      [datomic.api :as d]
      [clojure.string :refer [lower-case]]))

(defn create-schema
  "Create a Datomic schema (simple example)"
  [conn]
  (let [schema [{:db/id                 #db/id[:db.part/db]
                 :db/ident              :person/name
                 :db/valueType          :db.type/string
                 :db/cardinality        :db.cardinality/one
                 :db/doc                "The first name of the person"
                 :db.install/_attribute :db.part/db}

                {:db/id                 #db/id[:db.part/db]
                 :db/ident              :thing/name
                 :db/valueType          :db.type/string
                 :db/cardinality        :db.cardinality/one
                 :db/doc                "The last name of the person"
                 :db.install/_attribute :db.part/db}

                {:db/id                 #db/id[:db.part/db]
                 :db/ident              :thing/price
                 :db/valueType          :db.type/string
                 :db/cardinality        :db.cardinality/one
                 :db/unique             :db.unique/value
                 :db/doc                "The email of the person"
                 :db.install/_attribute :db.part/db}]]

 @(d/transact conn schema)))

(defn insert-seed-data [conn]
  (let [data {:db/id             (d/tempid :db.part/user)
              :person/name "mcattt"
              :thing/name  "marks"
              :thing/price      "fuckyou@fuckyou.com"}]
    @(d/transact conn (conj [] data))))


(defn seed-db [uri]
  (if-let [db (d/create-database uri)]
    (let [conn (d/connect uri)]
      (create-schema conn)
      (insert-seed-data conn))))
