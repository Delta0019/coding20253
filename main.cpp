#include <iostream>
#include <vector>

using namespace std;

int max_num = 1e9;

int main()
{
    int n = 0;
    cin >> n;
    vector<pair<int, int>> points{};
    for (int i = 0; i < n; i++)
    {
        int xi, yi;
        cin >> xi >> yi;
        pair<int, int> point{xi, yi};
        points.push_back(point);
    }
    auto it = points.front();
    cout << it.first << endl;
    return 0;
}