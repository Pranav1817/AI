# -*- coding: utf-8 -*-
"""Perceptron.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1la7tRdjWaJj_E-bgKw4sCwJN0vbtGA1p
"""

import numpy as np

# Define the activation function (Step function)
def activation_function(x):
    return 1 if x >= 0 else 0

# Define the Perceptron class
class Perceptron:
    def __init__(self, input_size):
        # Initialize weights with zeros and set the bias to 0
        self.weights = np.zeros(input_size)
        self.bias = 0

    def predict(self, inputs):
        # Calculate the weighted sum of inputs and add the bias
        weighted_sum = np.dot(inputs, self.weights) + self.bias
        # Apply the activation function
        prediction = activation_function(weighted_sum)
        return prediction

    def train(self, inputs, target, learning_rate=0.1, epochs=100):
        for _ in range(epochs):
            for i in range(len(inputs)):
                prediction = self.predict(inputs[i])
                error = target[i] - prediction
                # Update the weights and bias
                self.weights += learning_rate * error * inputs[i]
                self.bias += learning_rate * error

# Define the AND and OR gate input and target values
and_inputs = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])
and_targets = np.array([0, 0, 0, 1])
# Create Perceptrons for AND and OR gates
and_perceptron = Perceptron(input_size=2)

# Train the Perceptrons
and_perceptron.train(and_inputs, and_targets)

# Test the trained Perceptrons
test_inputs = np.array([[0, 0], [0, 1], [1, 0], [1, 1]])

print("AND Gate:")
for input_data in test_inputs:
    result = and_perceptron.predict(input_data)
    print(f"Input: {input_data}, Output: {result}")