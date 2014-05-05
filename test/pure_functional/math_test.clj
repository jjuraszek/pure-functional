(ns pure-functional.math-test
  (:require [clojure.test :refer :all]
            [pure-functional.math :refer :all]))

(deftest max-common-denominator-test
  (testing "for equal two numbers should return one of the number"
    (is (= 3 (max-common-denominator 3 3))))
  (testing "for one number divided by other one should return lower one"
    (is (= 3 (max-common-denominator 3 9))))
  (testing "for higher number which is not multiplication of lower keep doing mod"
    (is (= 2 (max-common-denominator 4 10))))
  (testing "for work for large number"
    (is (= 18 (max-common-denominator 461952 116298)))))
