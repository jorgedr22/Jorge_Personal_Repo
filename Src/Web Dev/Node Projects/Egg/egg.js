// Deck of cards
var suits = ["bastos","oro","copas","espadas"];
var values = ["1","2","3","4","5","6","7","10","11","12"];

let deck = new Array();

// Can only be player with 2-4 players (2 players min)
var player1 = [];
var player2 = [];
var player3 = [];
var player4 = [];

function getDeck(){
    
    for(let i=0;i<suits.length;i++){
        for(let j=0;j<values.length;j++){
            let card = {Value:values[j], Suit: suits[i]};
            deck.push(card)
        }
    }
    return deck;
}

function shuffle(){
    for(let i=0; i<100; i++){
        let loc1 = Math.floor((Math.random() * deck.length));
        let loc2 = Math.floor((Math.random() * deck.length));
        let tmp = deck[loc1];

        deck[loc1] = deck[loc2];
        deck[loc2] = tmp;
    }
}

function renderHand(){
    var br = document.createElement('br'); 

    if(document.querySelector("#players").value == 2){

        document.getElementById("deck").innerHTML = "";
        
        var button1 = document.createElement('input');
        button1.type = "button";
        button1.value = "Player 1";

        var button2 = document.createElement('input');
        button2.type = "button";
        button2.value = "Player 2";
        
        document.getElementById("deck").appendChild(button1).appendChild(br);

        for(var i = 0; i < player1.length; i++)
        {
            var card = document.createElement("div");
            var value = document.createElement("div");
            var suit = document.createElement("div");
            card.className = "card";
            value.className = "value";
            suit.className = "suit " + player1[i].Suit;

            value.innerHTML = player1[i].Value;
            card.appendChild(value);
            card.appendChild(suit);

            document.getElementById("deck").appendChild(card);
        }
        
        document.getElementById("deck").appendChild(br);
        document.getElementById("deck").appendChild(button2);

        for(var i = 0; i < player2.length; i++)
        {
            var card = document.createElement("div");
            var value = document.createElement("div");
            var suit = document.createElement("div");
            card.className = "card";
            value.className = "value";
            suit.className = "suit " + player2[i].Suit;

            value.innerHTML = player2[i].Value;
            card.appendChild(value);
            card.appendChild(suit);

            document.getElementById("deck").appendChild(card);
        }
        
    }
    else if(document.querySelector("#players").value == 3){

        document.getElementById("deck").innerHTML = "";
        
        var button1 = document.createElement('input');
        button1.type = "button";
        button1.value = "Player 1";

        var button2 = document.createElement('input');
        button2.type = "button";
        button2.value = "Player 2";

        var button3 = document.createElement('input');
        button3.type = "button";
        button3.value = "Player 3";
        
        document.getElementById("deck").appendChild(button1).appendChild(br);

        for(var i = 0; i < player1.length; i++)
        {
            var card = document.createElement("div");
            var value = document.createElement("div");
            var suit = document.createElement("div");
            card.className = "card";
            value.className = "value";
            suit.className = "suit " + player1[i].Suit;

            value.innerHTML = player1[i].Value;
            card.appendChild(value);
            card.appendChild(suit);

            document.getElementById("deck").appendChild(card);
        }
        
        document.getElementById("deck").appendChild(br);
        document.getElementById("deck").appendChild(button2);

        for(var i = 0; i < player2.length; i++)
        {
            var card = document.createElement("div");
            var value = document.createElement("div");
            var suit = document.createElement("div");
            card.className = "card";
            value.className = "value";
            suit.className = "suit " + player2[i].Suit;

            value.innerHTML = player2[i].Value;
            card.appendChild(value);
            card.appendChild(suit);

            document.getElementById("deck").appendChild(card);
        }
        
        document.getElementById("deck").appendChild(br);
        document.getElementById("deck").appendChild(button3);

        for(var i = 0; i < player3.length; i++)
        {
            var card = document.createElement("div");
            var value = document.createElement("div");
            var suit = document.createElement("div");
            card.className = "card";
            value.className = "value";
            suit.className = "suit " + player3[i].Suit;

            value.innerHTML = player3[i].Value;
            card.appendChild(value);
            card.appendChild(suit);

            document.getElementById("deck").appendChild(card);
        }
    }
    else if(document.querySelector("#players").value == 4){

        document.getElementById("deck").innerHTML = "";
        
        var button1 = document.createElement('input');
        button1.type = "button";
        button1.value = "Player 1";

        var button2 = document.createElement('input');
        button2.type = "button";
        button2.value = "Player 2";
        
        document.getElementById("deck").appendChild(button1).appendChild(br);

        for(var i = 0; i < player1.length; i++)
        {
            var card = document.createElement("div");
            var value = document.createElement("div");
            var suit = document.createElement("div");
            card.className = "card";
            value.className = "value";
            suit.className = "suit " + player1[i].Suit;

            value.innerHTML = player1[i].Value;
            card.appendChild(value);
            card.appendChild(suit);

            document.getElementById("deck").appendChild(card);
        }
        
        document.getElementById("deck").appendChild(br);
        document.getElementById("deck").appendChild(button2);

        for(var i = 0; i < player2.length; i++)
        {
            var card = document.createElement("div");
            var value = document.createElement("div");
            var suit = document.createElement("div");
            card.className = "card";
            value.className = "value";
            suit.className = "suit " + player2[i].Suit;

            value.innerHTML = player2[i].Value;
            card.appendChild(value);
            card.appendChild(suit);

            document.getElementById("deck").appendChild(card);
        }
        
    }
   

}

function playerHand(deck){
    
    if(document.querySelector("#players").value == 2){
        for(let i=0;i<11;i++){
            player1[i] = deck[(i*2)];
            player2[i] = deck[(i*2)+1];
        }
    
        for(let j=0;j<22;j++){
            delete deck[j];
        }
    }
    else if(document.querySelector("#players").value == 3){ 
        for(let i=0;i<9;i++){
            player1[i] = deck[(i*3)];
            player2[i] = deck[(i*3)+1];
            player3[i] = deck[(i*3)+2];
        }
    
        for(let j=0;j<27;j++){
            delete deck[j];
        }     
    }
    else if(document.querySelector("#players").value == 4){
        for(let i=0;i<7;i++){
            player1[i] = deck[(i*4)];
            player2[i] = deck[(i*4)+1];
            player3[i] = deck[(i*4)+2];
            player4[i] = deck[(i*4)+3];
        }
        for(let j=0;j<28;j++){
            delete deck[j];
        }                   
    }
    
}

function load(){
    deck = getDeck()
    shuffle();
    playerHand(deck);
    renderHand();
}
