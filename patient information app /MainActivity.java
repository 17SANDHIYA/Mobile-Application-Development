Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
intent.putExtra("name", name);
intent.putExtra("age", age);
intent.putExtra("gender", gender);
intent.putExtra("illness", illnessType);
intent.putExtra("date", date);
startActivity(intent);
