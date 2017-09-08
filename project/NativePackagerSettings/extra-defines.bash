for env_op in `env | grep -v ^MARATHON_APP | grep ^MARATHON_ | awk '{gsub(/MARATHON_/,""); gsub(/=/," "); printf("%s%s ", "--", tolower($1)); for(i=2;i<=NF;i++){printf("%s ", $i)}}'`; do
  addApp "$env_op"
done
