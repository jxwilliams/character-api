const heroes = [
  {
    name: "Iron Man",
    universe: "Marvel",
    power: "Powered armor",
    emoji: "🤖",
    bio: "Genius inventor in a high tech suit that gives flight weapons and defense."
  },
  {
    name: "Captain America",
    universe: "Marvel",
    power: "Super soldier",
    emoji: "🛡️",
    bio: "Enhanced strength speed "
  },
{
    name: "Spider Man",
    universe: "Marvel",
    power: "Spider abilities",
    emoji: "🕷️",
    bio: "Wall crawling super strength "
},
  {
    name: "Thor",
    universe: "Marvel",
    power: "God of thunder",
    emoji: "⚡",
    bio: "Asgardian warrior who controls lightning and fights with a legendary hammer."
  },
  {
    name: "Hulk",
    universe: "Marvel",
    power: "Rage strength",
    emoji: "💪",
    bio: "Gets stronger as he gets angrier super stregnth"
  },
  {
    name: "Superman",
    universe: "DC",
    power: "Kryptonian powers",
    emoji: "🦸",
    bio: "Super strength flight heat vision and near invulnerability powered by the sun."
  },
  {
    name: "Batman",
    universe: "DC",
    power: "Strategy and tech",
    emoji: "🦇",
    bio: "Master detective with top training gadgets and planning to take on anyone."
  },
  {
    name: "Wonder Woman",
    universe: "DC",
    power: "Amazon warrior",
    emoji: "🏛️",
    bio: "Super strength speed and combat skill with iconic tools and strong morals."
  },
  {
    name: "The Flash",
    universe: "DC",
    power: "Super speed",
    emoji: "💨",
    bio: "Moves at extreme speed with fast reflexes "
  }
];

var grid = document.getElementById("grid");
var search = document.getElementById("search");

function showList(list) {
  var html = "";
  var i = 0;

  if (list.length === 0) {
    grid.innerHTML = '<p class="desc">No matches found</p>';
    return;
  }

  while (i < list.length) {
    html += '<article class="card">';
    html += '<div class="thumb" aria-hidden="true">' + list[i].emoji + '</div>';
    html += '<div class="content">';
    html += '<h2 class="name">' + list[i].name + '</h2>';
    html += '<div class="tags">';
    html += '<span class="tag">' + list[i].universe + '</span>';
    html += '<span class="tag">' + list[i].power + '</span>';
    html += '</div>';
    html += '<p class="desc">' + list[i].bio + '</p>';
    html += '</div>';
    html += '</article>';
    i = i + 1;
  }

  grid.innerHTML = html;
}

function filterList(text) {
  var q = text.toLowerCase();
  var results = [];
  var i = 0;

  while (i < heroes.length) {
    var h = heroes[i];

    if (
      h.name.toLowerCase().indexOf(q) !== -1 ||
      h.power.toLowerCase().indexOf(q) !== -1 ||
      h.universe.toLowerCase().indexOf(q) !== -1
    ) {
      results.push(h);
    }

    i = i + 1;
  }

  return results;
}

if (search) {
  search.oninput = function () {
    var q = search.value.trim();

    if (q === "") {
      showList(heroes);
    } else {
      showList(filterList(q));
    }
  };
}

showList(heroes);