(ns pure-functional.simple-test
  (:require [clojure.test :refer :all]
            [pure-functional.simple :refer :all]))

(deftest common-denominator-test
  (testing "for equal two numbers should return one of the number"
    (is (= 3 (common-denominator 3 3)))))
