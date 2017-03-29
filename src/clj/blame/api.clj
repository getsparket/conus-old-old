(ns blame.api
  (:require [castra.core :refer [defrpc *session*]]
             [clojure.java.jdbc :as db]))

(def my-db {:dbtype "postgresql"
            :subname "postgresql://localhost:5432/pg"
            :dbname "pg"
            :user "matt"
            :password ""})

(defrpc new-owner-for-only-thing
  [owner]
  {:rpc/pre [(db/execute! my-db ["UPDATE things SET owner = ? where id = 10" (:owner owner)])]}
  (println "new-owner-for-only-thing being called") ;; logging....
  (get-only-thing))

#_(db/db-do-commands my-db (db/create-table-ddl
                          :blogs
                          [[:id :integer "PRIMARY KEY" "AUTO_INCREMENT"] ;; auto_increment throws an exception... 
                           [:title "varchar(255)"]
                           [:body :text]]))
#_(db/insert! my-db :blogs {:id 10 :title "my first entry" :body "body text"})
#_(db/query my-db "select * from blogs")

(defrpc get-only-thing
  []
  (first (db/query my-db "SELECT * FROM things WHERE id = 10")))
