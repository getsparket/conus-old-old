(ns demo.datomic.seed
    (:require
      [datomic.api :as d]
      [clojure.string :refer [lower-case]]))

(defn create-schema
  "Create a Datomic schema (simple example)"
  [conn]
  (let [schema [{:db/id                 #db/id[:db.part/db]
                 :db/ident              :person/user-name
                 :db/valueType          :db.type/string
                 :db/cardinality        :db.cardinality/one
                 ;; :db/unique             :db.unique/value
                 :db/doc                "username"
                 :db.install/_attribute :db.part/db}

                #_{:db/id                 #db/id[:db.part/db]
                 :db/ident              :thing/short-desc
                 :db/valueType          :db.type/string
                 :db/cardinality        :db.cardinality/one
                 :db/doc                "The last name of the person"
                 :db.install/_attribute :db.part/db}

                #_{:db/id                 #db/id[:db.part/db]
                 :db/ident              :thing/price
                 :db/valueType          :db.type/bigdec
                 :db/cardinality        :db.cardinality/one
                 :db/doc                "price"
                 :db.install/_attribute :db.part/db}]]

 @(d/transact conn schema)))

(defn insert-seed-data [conn n]
  (let [user-name (rand-nth ["Alice" "Bob" "Claire" "Dustin" "Ellen" "Fred" "Georgia"])
        ;; short-desc (rand-nth ["MBP 15 2015 base model" "Audeze LCD-2" "space gray 6S+ 128gb" "IBM model f AT" "89 shitty chevy sport van" "Sennheiser HD600 ok condition" "Clyfford Still"])
        ;; price (rand-nth [750 600 450 100 400 60 60])

        data {:db/id             (d/tempid :db.part/user)
              :person/user-name user-name
              }
              ]
    @(d/transact conn (conj [] data))))

(defn seed-db [uri]
  (if-let [db (d/create-database uri)]
    (let [conn (d/connect uri)]
      (create-schema conn)
      (doall (map #(insert-seed-data conn %) (range 4))))))
