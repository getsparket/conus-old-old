(ns blame.api
  (:require [castra.core :refer [defrpc *session*]]
             [clojure.java.jdbc :as db]))

(def my-db {:dbtype "postgresql"
            :subname "postgresql://localhost:5432/pg"
            :dbname "pg"
            :user "matt"
            :password ""})

(defrpc new-owner-for-thing
  [owner]
  {:rpc/pre [(db/execute! my-db ["UPDATE things SET owner = ? where id = ?" (:owner owner) (read-string (:id owner))])]}
  (println "new-owner-for-thing being called")) ;; logging.....

(defrpc add-another-thing
  [thing]
  (let [with-ints (assoc thing :id (read-string (:id thing)) :price (read-string (:price thing)))] ;; TODO remove read-string
        {:rpc/pre [(db/insert! my-db :things with-ints )]}
  (println "add-another-thing being called" thing))) ;; logging.....
#_(db/db-do-commands my-db (db/create-table-ddl
                          :blogs
                          [[:id :integer "PRIMARY KEY" "AUTO_INCREMENT"] ;; auto_increment throws an exception... 
                           [:title "varchar(255)"]
                           [:body :text]]))
#_(db/insert! my-db :blogs {:id 10 :title "my first entry" :body "body text"})
#_(db/query my-db "select * from blogs")

(defrpc get-thing
  [id]
  (first (db/query my-db ["SELECT * FROM things WHERE id = ?" (:id id)])))
