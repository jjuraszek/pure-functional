(ns pure-functional.find-test
  (:require [clojure.test :refer :all]
            [pure-functional.find :refer :all]))

(deftest triple-to-zero-test
  (testing "should find 1 set summing to zero"
    (is (= 1 (triple-to-zero [2 3 -5]))))
  (comment (testing "should find 2")
    (is (= 2 (triple-to-zero [3 2 4 -5 -6])))))

(deftest count-summing-pairs-test
  (testing "should find count even if pairs are separated"
    (is (= 2 (count-summing-pairs [-1 1 2 4] 3)))))