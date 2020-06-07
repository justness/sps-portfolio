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

function scramble(){
    var title = new String(document.getElementById('title').innerText);
    var temp = new String(title);
    for (var i=0;i<temp.length-1;i++){
        var coinFlip = Math.random() >= 0.5;
        if (coinFlip == false){
            temp = temp.substr(0,i) + temp.charAt(i).toUpperCase() + temp.slice(i+1);
        }
        else{
            temp = temp.substr(0,i) + temp.charAt(i).toLowerCase() + temp.slice(i+1);
        }
    }
    document.getElementById('title').innerHTML = temp;
}
function unscramble(){
    var title = new String(document.getElementById('title').innerText);
    var temp = new String(title);
    for (var i=0;i<title.length-1;i++){
        temp = temp.substr(0,i) + temp.charAt(i).toLowerCase() + temp.slice(i+1);
    }
    document.getElementById('title').innerHTML = temp;
}