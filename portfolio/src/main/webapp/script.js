// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Gives a random number from 1-6.
 */
function addRandomNumber() {
  const numbers =
      ['1', '2', '3', '4', '5', '6'];

  // Pick a random number.
  const number = numbers[Math.floor(Math.random() * numbers.length)];

  // Add it to the page.
  const numberContainer = document.getElementById('number-container');
  numberContainer.innerText = number;
}
