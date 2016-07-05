# (c) Ben Limmer, 2013, released under cc-by 4.0 (https://creativecommons.org/licenses/by/4.0/)
# source: https://benlimmer.com/2013/12/26/automatically-publish-javadoc-to-gh-pages-with-travis-ci/

# Get to the Travis build directory, configure git and clone the repo
#cd $HOME
git config --global user.email "travis@travis-ci.org"
git config --global user.name "travis-ci"
git clone --branch=gh-pages https://${GH_TOKEN}@github.com/ProPra16/programmierpraktikum-abschlussprojekt-team-1 gh-pages
ls -ahl
ls -ahl gh-pages

# Commit and Push the Changes
cd gh-pages
git rm -rf ./javadoc
cp -Rf ../TDD-Trainer/doc ./javadoc
git add -f .
git commit -m "Lastest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
git push -fq origin gh-pages > /dev/null

