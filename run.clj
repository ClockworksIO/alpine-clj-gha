(ns run
  "Setup stuff for the action."
  (:require
    [babashka.process :refer [sh]]
    [clojure.string :as str]))

(def clj-path "/usr/local/bin/clojure")
(def clj-version (-> (sh "clojure --version") :out (str/split #" ") last))

(def babashka-path "/usr/local/bin/bb")
(def babashka-version (-> (sh "bb --version") :out (str/split #" ") last))

(def java-version (-> (sh "java -version") :out str/split-lines first))

(sh (format "echo \"java_version=%s\" >> $GITHUB_OUTPUT" java-version))
(sh (format "echo \"clojure_path=%s\" >> $GITHUB_OUTPUT" clj-path))
(sh (format "echo \"clojure_version=%s\" >> $GITHUB_OUTPUT" clj-version))
(sh (format "echo \"babashka_path=%s\" >> $GITHUB_OUTPUT" babashka-path))
(sh (format "echo \"babashka_version=%s\" >> $GITHUB_OUTPUT" babashka-version))