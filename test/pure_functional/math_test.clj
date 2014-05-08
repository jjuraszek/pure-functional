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

(deftest multiply-matrix-test
  (testing "should return multiplication of two numbers for 1x1 * 1x1"
    (is (= [[6]] (multiply-matrix [[2]] [[3]]))))
  (testing "should multiply two vectors 1xn and nx1 and accumulate it to one number"
    (is (= [[20]] (multiply-matrix [[2 3 1]] [[5][2][4]]))))
  (testing "should multiply matrix 3x2 by 2x1"
    (is (= [[23][24][77]] (multiply-matrix [[2 3][1 4][8 9]] [[4][5]]))))
  (testing "should multiply matrix 2x2 by 2x3"
    (is (= [[90 120 150][190 260 330]] (multiply-matrix [[10 20][30 40]] [[1 2 3][4 5 6]])))))
